package com.exam;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static int[] sort(Sortable sortable, int ... array){
        return sortable.sort(array);
    }

    public static int find(Findable findable, int searchFor, int ... array){
        return findable.find(searchFor,array);
    }

    private static int firstIndexOf(Findable findable, int searchFor, int...array) {
        return findable.find(searchFor,array);
    }

    public static int promptTheUserToEnterADigit(String message) {
        System.out.println(message);
        Scanner scanner=new Scanner(System.in);
        int num=scanner.nextInt();
        return num;
    }

    public static void swap(int[] arr, int firstIndex, int secondIndex) {
        int buf = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = buf;
    }

    public static void printArr(int[] arr){
        System.out.print("[  ");
        for (int i=0;i<arr.length;i++){
            System.out.print(arr[i]+"  ");
        }
        System.out.print("]\n");
    }

    public static int  linearSearchImpl(int toSearchFor,int [] mas){
        for (int i=0;i<mas.length;i++){
            if (mas[i]==toSearchFor){
                return i;
            }
        }
        return -1;
    }

    public static int[] bubbleSortImpl(int ... mas){
        boolean isSorted = false;
        while(!isSorted) {
            isSorted = true;
            for (int i = 0; i < mas.length-1; i++) {
                if(mas[i] > mas[i+1]){
                    isSorted = false;
                    swap(mas,i,i+1);
                }
            }
        }
        return mas;
    }

    public static int[] selectionSortImpl(int ... mas) {
        for (int left = 0; left < mas.length; left++) {
            int value = mas[left];
            int i = left - 1;
            for (; i >= 0; i--) {
                if (value < mas[i]) {
                    mas[i + 1] = mas[i];
                } else {
                    break;
                }
            }
            mas[i + 1] = value;
        }
        return mas;
    }

    public static int binarySearchImpl(int searchFor,int[] mas) {
        StackTraceElement [] stackTraceElements=Thread.currentThread().getStackTrace();

        int low=0;
        int high=mas.length-1;

        while (low<=high) {

            int mid=(low+high)/2;
            int guess=mas[mid];

            if (guess < searchFor) {
                low = mid + 1;
            }

            if (guess > searchFor) {
                high = mid - 1;
            }

            if (guess == searchFor) {
                if (stackTraceElements[2].getMethodName().equals("firstIndexOf")) {
                    for (int i = mid; i > -1; i--) {
                        if (mas[i - 1] != searchFor) {
                            return i;
                        }
                    }
                }
                return mid;
            }
        }
        return -1;
    }

    public static int[] generateAnArray(int length,int startNum,int lastNum){
        int[] arr=new int[length];
        Random random=new Random();
        for (int i=0;i<arr.length;i++){
            arr[i]=random.nextInt(lastNum-startNum+1)+startNum;
        }
        return arr;
    }

    public static void main(String[] args) {
        int length=promptTheUserToEnterADigit("Enter count of numbers:");
        int startNum=promptTheUserToEnterADigit("Enter the range of numbers\nEnter start number (min):");
        int lastNum=promptTheUserToEnterADigit("Enter last number (max):");
        int[]array = generateAnArray(length,startNum,lastNum);
        int[] sortedArray = sort(Main::selectionSortImpl, array);
        System.out.println("your  sorted array  elements are printed below:");
        printArr(sortedArray);

        int searchFor = promptTheUserToEnterADigit("enter a digit to search for");
        int indexOfSearchFor = find(Main::binarySearchImpl, searchFor, array);
        System.out.println("index of an element: " + indexOfSearchFor);
        int firstIndexOf = firstIndexOf(Main::binarySearchImpl, searchFor, array);
        System.out.println("first index of an element: " + firstIndexOf);
    }
}
