package main.blueprint;

import java.io.File;
import java.util.Scanner;

public class BlueprintFileReader 
{
    private BlueprintFileReader()
    {

    }

    public static Blueprint load(String filePath) throws Exception
    {
        File file = new File(filePath);
        Scanner scanFile = new Scanner(file);
        String blueprintString = "";

        while(scanFile.hasNext()) {
            blueprintString += scanFile.next();
        }

        scanFile.close();
        
        Blueprint blueprint = new Blueprint(blueprintString);
        return blueprint;
    }
}