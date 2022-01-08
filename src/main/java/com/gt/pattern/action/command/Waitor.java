package com.gt.pattern.action.command;

import java.util.ArrayList;
import java.util.List;

/**
 * @author GTsung
 * @date 2022/1/8
 */
public class Waitor {

    private List<Command> commands = new ArrayList<>();

    public void setCommand(Command command) {
        commands.add(command);
    }

    public void orderUp() {
        System.out.println("新订单来了");

        commands.forEach(c -> {
            if (c != null) {
                c.execute();
            }
        });
    }
}
