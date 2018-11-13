/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bubblesort;

import java.util.*;
public class BubbleSort{

    public static void main(String[] args) {
        //Defining a Linked list
        Node first= new Node("abc");
        MyLinkedList dataList = new MyLinkedList (first);
        Node second= new Node("bc");
        Node third= new Node("defc");
        Node forth= new Node("adf");    
        Node fifth= new Node("adfa"); 
        Node sixth= new Node("defc");
        first.next = second;
        second.next= third;
        third.next=forth;
        forth.next=fifth;
        fifth.next=sixth;
        
      //Tests: printing out Linked list before sorting.
       System.out.println(dataList.toString());
       // RUnning tests for size method in MyLinkedList
       System.out.println (dataList.size());
       // Bubble sorting and printing out the Linked List after sorting
       BubbleSort (dataList);
       System.out.println(dataList.toString());
    }
    // Defining class Node
    public static class Node {
        String item;
        Node next;
    // Constructor for class Node for faster definition    
    public Node(String a){
            next=null;
            item= a;
        }
     // returns information stored in a node (String of information in this case)   
    public String getItem(){
        return item;
    }
    // Returns a node after the current one
    public Node getNext(){
        return next;
    }
    }
    
    /*Definition of the method swap. According to the task I was supposed to pass MyLinkedList too
    and it was a bit unclear whether I should swap the nodes or just values. In order to make algorithm 
    more efficient I have implemented "swap" just to change the values inside the nodes and in order to
    do so I did not need to pass MyLinkedList */
    public static void  swap ( Node a, Node b){
        String temp =a.item;
        a.item=b.item;
        b.item=temp;
    }
    
   
    // Bubblesort: implementing algorithm according to the pseudocode
    public static void BubbleSort (MyLinkedList a){
    int R = a.size()-2;
    boolean swapped= true;   
    while (R >= 0 && swapped == true ){
        swapped= false;
        for (Node x = a.first ; x.next != null; x = x.next){
            Node g= x.getNext();
            if ( x.getItem().compareTo(g.getItem()) > 0 ){
                swapped= true;
               swap (x,g);
            }
        }
        R--;
    }
}
    // Definition of linked list with size method
    static class MyLinkedList {
        public Node first;
        public int listCount;
        public MyLinkedList (Node a){
            first= a;
            listCount=0;
        }
        public int size (){
          for  (Node x = first ; x != null; x = x.next) {
            listCount++;
        }
          return listCount;
        } 
        //Method toString for MyLinkedList, prints out "Contents:" and inofrmation from each node.(each on a new line)
        @Override
        public String toString(){
        String retString = "Contents:\n";
        Node current = first;
        while(current != null){ 
            retString += current.getItem() + "\n";
            current = current.getNext();
        }
        return retString;
    }
    }   
}