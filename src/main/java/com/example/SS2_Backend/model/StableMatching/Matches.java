package com.example.SS2_Backend.model.StableMatching;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Data Container for Algorithm Result
 * Matches = {Match1, Match2, Match3, ...}
 * Match maybe an Object of "Pair" or "MatchSet" Class, both Implement "MatchItem" Interface
 */

public class Matches implements Serializable {
    private static final long serialVersionUID = 1L;
    private final List<MatchItem> matches = new LinkedList<>();

    public Matches(){
    }

    public void add(MatchItem match){
        matches.add(match);
    }
    public MatchItem getPair(int index){
        return matches.get(index);
    }
    public String findCompany(int target){
        String company = null;
        for(int i = 0; i < matches.size(); i++){
            if(matches.get(i).getIndividual1Index() == target){
                company = Integer.toString(matches.get(i).getIndividual2Index());
                break;
            }else if(matches.get(i).getIndividual2Index() == target){
                company = Integer.toString(matches.get(i).getIndividual1Index());
                break;
            }
        }
        return company;
    }

    public int size(){
        return matches.size();
    }

    public void disMatch(int target){
        for(int i = 0; i < matches.size(); i++){
            if(matches.get(i).getIndividual1Index() == target){
                matches.remove(i);
                break;
            }else if(matches.get(i).getIndividual2Index() == target){
                matches.remove(i);
                break;
            }
        }
    }
    public int[] parseArray(){
        int[] a = new int[matches.size()*2];
        for(int i=0; i < a.length;i++){
            a[i] = matches.get(i).getIndividual1Index();
            a[i+1] = matches.get(i).getIndividual2Index();
            i+=2;
        }
        return a;
    }

    public Matches parseMatches(int[] array){
        Matches matches = new Matches();
        for(int i = 0; i < array.length; i++){
            int a = array[i];
            int b = array[i+1];
            matches.add(new Pair(a, b));
            i+=2;
        }
        return matches;
    }

    public String toString(){
        StringBuilder s = new StringBuilder();
        s.append("{\n");
        for(int i = 0; i < matches.size(); i++){
            s.append("[");
            s.append(matches.get(i).toString());
            s.append("]\n");
        }
        s.append("}");
        return s.toString();
    }

    public static void main(String[] args){
        Matches matches = new Matches();
        matches.add(new Pair(1,2));
        matches.add(new Pair(3,4));
        matches.add(new Pair(5,6));

        System.out.println(matches.toString());
    }
}