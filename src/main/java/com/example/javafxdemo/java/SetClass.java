package com.example.javafxdemo.java;

import java.util.ArrayList;


interface Identify{
    int getId();
}
class Implementation implements Identify{
    private int id;
    private String value;
    public Implementation(int id, String value){
        this.id=id;
        this.value=value;
    }
    public String toString(){
        return value;
    }

    @Override
    public int getId() {
        return id;
    }
}

public class SetClass<T extends Identify> {
    private ArrayList<T> set;
    public SetClass(){
        set= new ArrayList<T>();
    }
   public boolean peek(int id){
        for(T key:set){
            if(key.getId()==id) return true;
        }
        return false;
    }
    public void add(T obj){
        if(!peek(obj.getId())){
            set.add(obj);
        }
    }
    public T remove(T obj){
        if(!peek(obj.getId())){
            return null;
        }
        else{
            for(int i =0;i<set.size();i++){
                T temp = set.get(i);
                if(temp.getId()==obj.getId()) {
                    set.remove(i);
                    return temp;
                }
            }
            return null;
        }
        
    }
    public T remove(int id){
       
        for(int i =0;i<set.size();i++){
            T temp = set.get(i);
            if(temp.getId()==id) {
                set.remove(i);
                return temp;
            }
        }
        return null;
        
    }
    public int getSize(){
        return set.size();
    }
    public boolean equals(Object o){
        SetClass<?> temp = (SetClass<?>) o;
        if(this.getSize()==temp.getSize()){
            for(int i=0;i< temp.getSize();i++){
                if(!this.set.contains(temp.set.get(i)))return false;
            }
            return true;
        }else return false;
    }
    public void display(){
        for(int i=0;i<set.size();i++){
            System.out.println(set.get(i));
        }
    }
    public static void main(String[] args){
        Implementation i1 = new Implementation(1,"fd1");
        Implementation i2 = new Implementation(3,"fd3");
        Implementation i3 = new Implementation(2,"fd2");
        Implementation i4 = new Implementation(1,"fd1");
        Implementation i5 = new Implementation(4,"fd4");
        SetClass<Implementation> set = new SetClass<>();
        set.add(i1);
        set.add(i2);
        set.add(i3);
        set.add(i4);
        set.add(i5);
        SetClass<Implementation> set2 = new SetClass<>();
        set2.add(i2);
        set2.add(i3);
        set2.add(i1);
        
        set2.add(i4);
        set2.add(i5);
        System.out.println("set1 elements");
        set.display();
        System.out.println("set2 elements");
        set2.display();
        System.out.println("set equality: "+set.equals(set2));
        System.out.println("remove element from set2");
        set2.remove(i5);
        set2.remove(2);
        set2.display();
        System.out.println("set equality: " +set.equals(set2));
    }
}