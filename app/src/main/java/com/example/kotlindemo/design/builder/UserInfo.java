package com.example.kotlindemo.design.builder;

public class UserInfo {

    private String name;
    private int age;

    private UserInfo(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static class Builder {
        private String name;
        private int age;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public UserInfo build() {
            return new UserInfo(this);
        }

    }

    public static void main(String[] args) {
        UserInfo.Builder builder = new UserInfo.Builder();
        UserInfo info = builder.setAge(10).setName("xiaomi").build();
        System.out.println(info.getAge() + "---" + info.getName());
    }


}
