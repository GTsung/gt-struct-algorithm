package com.gt.netty.action.server.session;

import lombok.Data;

import java.util.Collections;
import java.util.Set;

/**
 * @author GTsung
 * @date 2022/1/14
 */
@Data
public class Group {

    private String name;

    private Set<String> members;

    public static final Group EMPTY_GROUP = new Group("empty", Collections.EMPTY_SET);

    public Group(String name, Set<String> members) {
        this.name = name;
        this.members = members;
    }

}
