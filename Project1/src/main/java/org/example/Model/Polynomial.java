package org.example.Model;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Polynomial {
    private final Map<Integer, Double> polyMap;

    public Polynomial(String polyString) {
        polyMap = new HashMap<>();

        Pattern pattern = Pattern.compile("((([-+]?)([1-9]{0,5}))(x)(\\^([0-9]{1,5}))?)|([-+]?[1-9]{1,5})");
        Matcher matcher = pattern.matcher(polyString);

        while (matcher.find()) {
            int coefficient = 0;
            int exponent = 0;
            if (matcher.group(5) != null){
                if(matcher.group(2) != null && !Objects.equals(matcher.group(2), "")) {
                    if("-".equals(matcher.group(3))){
                        if(matcher.group(4)!=null && !Objects.equals(matcher.group(4), "")){
                            coefficient = -1 * Integer.parseInt(matcher.group(4));
                        }
                        else{
                            coefficient = -1;
                        }
                    }
                    else if ("+".equals(matcher.group(3))) {
                        if(matcher.group(4)!=null  && !Objects.equals(matcher.group(4), "")){
                            coefficient = Integer.parseInt(matcher.group(4));
                        }
                        else{
                            coefficient = 1;
                        }
                    }
                    else{coefficient = Integer.parseInt(matcher.group(4));}
                }
                else{coefficient = 1;}
                if (matcher.group(6) != null && matcher.group(7) != null) {exponent = Integer.parseInt(matcher.group(7));}
                else{exponent = 1;}
            }
            else{
                if(matcher.group(8) != null && !Objects.equals(matcher.group(8), "")){coefficient = Integer.parseInt(matcher.group(8));}
            }
            if (coefficient != 0) {
                polyMap.put(exponent, polyMap.getOrDefault(exponent, 0.0) + coefficient);
            }
        }
    }

    public static Boolean validateInput(String a){
        if(a.isEmpty()){
            return false;
        }
        Pattern pattern = Pattern.compile("^[a-wyzA-WYZ]+|([+-][+-]+)|(\\^$)|(\\^\\^+)|( )");
        Matcher matcher = pattern.matcher(a);

        return !matcher.find();
    }
    public static String convString(Polynomial poly) {
        Map<Integer, Double> coeffs = poly.polyMap;
        StringBuilder sb = new StringBuilder();

        if (coeffs.isEmpty()) {return "0";}

        boolean firstTerm = true;

        for (int exp : coeffs.keySet()) {
            Double coeff = coeffs.get(exp);
            if (coeff == 0) {continue;}
            if (!firstTerm) {
                if (coeff > 0) {sb.append("+");}
                else {
                    sb.append("-");
                    coeff = -coeff;
                }
            }
            if (coeff != 1 || exp == 0)
            {
                if(firstTerm && coeff == -1){
                    sb.append("-");
                    coeff=1.0;
                }
                if (coeff % 1 == 0) {
                    sb.append(coeff.intValue());
                } else {
                    long roundedInt = Math.round(coeff * 100);
                    double result = (double) roundedInt/100;
                    sb.append(result);
                }
            }
            if (exp > 0) {
                sb.append("x");
                if (exp > 1) {sb.append("^").append(exp);}
            }
            firstTerm = false;
        }
        if(sb.isEmpty())
            sb.append(0);
        return sb.toString();
    }

    public static String add(String poly1, String poly2) {
        Polynomial p1 = new Polynomial(poly1);
        Polynomial p2 = new Polynomial(poly2);
        Polynomial result = new Polynomial("");

        for (int exponent : p1.polyMap.keySet()) {
            Double coefficient = p1.polyMap.get(exponent);
            result.polyMap.put(exponent, coefficient);
        }

        for (int exponent : p2.polyMap.keySet()) {
            Double coefficient = p2.polyMap.get(exponent);
            if (result.polyMap.containsKey(exponent)) {
                coefficient += result.polyMap.get(exponent);
            }
            result.polyMap.put(exponent, coefficient);
        }
        return convString(result);
    }

    public static String subtract(String poly1, String poly2){
        Polynomial p3 = new Polynomial(poly1);
        Polynomial p4 = new Polynomial(poly2);
        Polynomial result = new Polynomial("");

        for (int exponent : p3.polyMap.keySet()) {
            Double coefficient = p3.polyMap.get(exponent);
            result.polyMap.put(exponent, coefficient);
        }

        for (int exponent : p4.polyMap.keySet()) {
            Double coefficient = p4.polyMap.get(exponent);
            if (result.polyMap.containsKey(exponent)) {
                coefficient -= result.polyMap.get(exponent);
            }
            result.polyMap.put(exponent, -1*coefficient);
        }
        return convString(result);
    }

    public static String multiply(String poly1, String poly2) {
        Polynomial p1 = new Polynomial(poly1);
        Polynomial p2 = new Polynomial(poly2);
        Polynomial result = new Polynomial("");
        for (int pwr1 : p1.polyMap.keySet()) {
            Double coeff1 = p1.polyMap.get(pwr1);

            for (int pwr2 : p2.polyMap.keySet()) {
                Double coeff2 = p2.polyMap.get(pwr2);
                int resultPwr = pwr1 + pwr2;
                Double resultCoeff = coeff1 * coeff2;
                result.polyMap.merge(resultPwr, resultCoeff, Double::sum);
            }
        }
        return  convString(result);
    }

    public static String[] division(String poly1, String poly2) {
        if(Objects.equals(poly1, "0")){
            return new String[]{"0", poly2};
        }
        if(Objects.equals(poly2, "0")){
            return null;
        }
        Polynomial d = new Polynomial(poly2);
        Polynomial q = new Polynomial("");
        Polynomial r = new Polynomial(poly1);

        while(!r.polyMap.isEmpty() && r.degree(r.polyMap)>=d.degree(d.polyMap)){
            Polynomial t = new Polynomial("");
            t.polyMap.put(r.degree(r.polyMap)-d.degree(d.polyMap), r.coefficient(r.degree(r.polyMap)) / d.coefficient(d.degree(d.polyMap)));
            q = new Polynomial(Polynomial.add(convString(q), convString(t)));
            r = new Polynomial(Polynomial.subtract(convString(r), Polynomial.multiply(convString(t),poly2)));
            for(Map.Entry<Integer, Double> i : r.polyMap.entrySet()){
                if(i.getValue()==0) {r.polyMap.remove(i.getKey());}
            }
        }

        return new String[] {convString(q), convString(r)};
    }

    public int degree(Map<Integer, Double> map) {
        int maxPower = 0;
        for (int power : map.keySet()) {
            if (power > maxPower) {
                maxPower = power;
            }
        }
        return maxPower;
    }

    public double coefficient(int power) {
        return polyMap.getOrDefault(power, 0.0);
    }

    public static String integrate(String poly) {
        Polynomial p = new Polynomial(poly);
        Map<Integer, Double> polyMap = p.polyMap;
        Map<Integer, Double> copy = new HashMap<>();
        for (Map.Entry<Integer, Double> term : polyMap.entrySet()) {
            int power = term.getKey();
            Double coeff = term.getValue();
            copy.put(power + 1, coeff / (power + 1));
        }
        polyMap.clear();
        polyMap.putAll(copy);
        return convString(p);
    }
    public static String differentiate(String poly) {
        Polynomial p = new Polynomial(poly);
        Map<Integer, Double> polyMap = p.polyMap;
        Map<Integer, Double> copy = new HashMap<>();
        for (Map.Entry<Integer, Double> term : polyMap.entrySet()) {
            int power = term.getKey();
            Double coeff = term.getValue();
            if (power != 0) {
                copy.put(power - 1, coeff * power);
            }

        }
        polyMap.clear();
        polyMap.putAll(copy);
        return convString(p);
    }
}
