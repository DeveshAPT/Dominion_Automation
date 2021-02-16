package pageHelper.web;

import java.util.ArrayList;
import java.util.List;

public class CommonFunctions
{
    public CommonFunctions()
    {}
    public String RemoveAllSpace(String text)
    {
        String temp=text.replaceAll("\\s", "");
        return  temp;
    }
    public List<String> RemoveSpaceOfAllItems(List<String> items)
    {
        List<String> newItems= new ArrayList<>();
        for(int i=0;i<items.size();i++)
        {
            newItems.add(RemoveAllSpace(items.get(i)));
        }
        return  newItems;
    }
}
