package chapter_13;

public interface Test_Interface {

    boolean test_function_1(String string);

    default String test_Func_2(int num){
       return "";
    }
}
