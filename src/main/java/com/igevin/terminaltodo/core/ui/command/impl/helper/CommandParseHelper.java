package com.igevin.terminaltodo.core.ui.command.impl.helper;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class CommandParseHelper {
    private int getFirstSpace(String line) {
        int firstSpace = line.indexOf(" ");
        if (firstSpace == -1) {
            throw new RuntimeException("命令行参数错误");
        }
        return firstSpace;
    }

    public long parseTaskId(String line) {
        int firstSpace = getFirstSpace(line);
        return Long.parseLong(line.substring(firstSpace + 1).trim());
    }

    public List<String> getAsNSegments(String line, int n) {
        List<String> originSegments = Arrays.asList(line.split(" "));
        if (originSegments.size() < n) {
            return originSegments;
        }
        String rest = String.join(" ", originSegments.subList(n - 1, originSegments.size()));
        originSegments.set(n - 1, rest);
        return originSegments.subList(0, n);
    }
}
