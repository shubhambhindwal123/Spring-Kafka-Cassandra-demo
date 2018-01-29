package com.exa.pojo;
public class Tenant
{
    private String id;

    private String tenantname;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getTenantname ()
    {
        return tenantname;
    }

    public void setTenantname (String tenantname)
    {
        this.tenantname = tenantname;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [id = "+id+", tenantname = "+tenantname+"]";
    }
}