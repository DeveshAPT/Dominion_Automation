package pageHelper.web;

public class CommonFunctions
{
    public CommonFunctions()
    {}
    public String RemoveAllSpace(String text)
    {
        String temp=text.replaceAll("\\s", "");
        return  temp;
    }
}
