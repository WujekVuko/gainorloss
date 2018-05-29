package webapp.sharetransactions.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import webapp.sharetransactions.model.ShareName;

import java.util.*;

@Controller
public class NameController {

    /**
     * This method will return same set of share names all the time.
     *
     * @return Map<String, List<ShareNames>> represetns a json name value pair
     */
    @RequestMapping(value="/ajaxNameSearch", method = RequestMethod.GET)

    @ResponseBody
    public Map<String, List<ShareName>> getShareName(){
        List<ShareName> sharename = new ArrayList<ShareName>(Arrays.asList(new ShareName("Famur","FAMUR," )));
        Map<String, List<ShareName>> shareMap = new HashMap<String, List<ShareName>>();
        shareMap.put("sharename", sharename);
        return shareMap;
    }

}
