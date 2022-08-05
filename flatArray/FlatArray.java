package br.com.rmxs.intercom;


import java.util.ArrayList;
import java.util.List;

public class FlatArray {
    public static void main(String[] args) {
        /** Array to be flattened **/
        /*Object[] inputArray = new Object[]{
                new Object[]{1, 2, new Object[]{3}},
                new Object[]{4}
        };*/

        Object[] inputArray = new Object[]{
                new Object[]{1, 2, new Object[]{3, new Object[]{5,6,new Object[]{7,8,9}}}},
                new Object[]{4, 10, new Object[]{11}}
        };

        Object[] flatArray = flattenArray(inputArray);
        showResult(flatArray);

    }

    /**
     * Process the matrix to be flattened.
     * @param array - Array to be processed;
     * @return the flat array;
     */
    public static Object[] flattenArray(Object[] array){
        List<Integer> list  = new ArrayList<Integer>();

        for (int j = 0; j < array.length; j++) {
            Object a = getChild(array[j], list);
            if(a != null) {
                list.add((int) a);
            }
        }
        return list.toArray();
    }


    /**
     * Search the object which is an integer and add it to a list.     *
     * @param obj - the item from the list;
     * @param list - a list which will save the integers;
     * @return
     */
    private static Integer getChild(Object obj, List list) {
        if(obj instanceof Integer){
            list.add((Integer) obj);
            return (Integer) obj;
        }else{
            Object[] a = (Object[]) obj;
            for (int j = 0; j < a.length; j++) {
                getChild(a[j], list);
            }
            return null;
        }
    }

    /**
     * Print the result
     * @param list - flat array
     */
    public static void showResult(Object[] list){
        String comma= ",";
        System.out.print("array = {");
        for (int i = 0 ; i < list.length ; i++){
            if(i >= list.length - 1){
                comma = "";
            }
            System.out.print(list[i] + comma);
        }
        System.out.print("};");
    }

}