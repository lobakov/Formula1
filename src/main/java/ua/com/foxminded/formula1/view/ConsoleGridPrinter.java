package ua.com.foxminded.formula1.view;

public class ConsoleGridPrinter implements GridPrinter {

    @Override
    public void print(String race) {
        System.out.println(race);
    }
}
