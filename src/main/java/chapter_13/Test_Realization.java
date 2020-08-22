package chapter_13;

public class Test_Realization implements Test_Interface, Interface_Test_2 {

    @Override
    public boolean test_function_1(String string) {
        return false;
    }

    @Override
    public String test_Func_222(int num) {
        return null;
    }

    @Override
    public String test_Func_2(int num){
        return "";
    }
}
