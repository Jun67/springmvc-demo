package com.bailiban.socket.niohttptest2.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private int id;
    private String name;

    public static void main(String[] args) {
        String line = "<div>id: ${user.id}</div><div>name: ${user.name}</div>";
        System.out.println(line.matches(".*?\\$\\{(.*?)\\}.*?"));
        Pattern pattern = Pattern.compile("\\$\\{(.*?)\\}");
        Matcher m = pattern.matcher(line);
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            System.out.println(m.group(1));
            System.out.println("start(): "+m.start());
            System.out.println("end(): "+m.end());
            m.appendReplacement(sb, "1010");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
    }
}
