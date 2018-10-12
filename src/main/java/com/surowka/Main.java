package com.surowka;

import static spark.Spark.get;

public class Main {
    public static void main(String[] args) {
        get("/", (req, res) -> {
            return "hello from sparkjava.com";
        });
    }
}
