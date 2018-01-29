package com.exa.pojo;
public class Assistant
{
    private String id;

    private String username;

    private String fullName;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getFullName ()
    {
        return fullName;
    }

    public void setFullName (String fullName)
    {
        this.fullName = fullName;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", username = "+username+", fullName = "+fullName+"]";
    }
}