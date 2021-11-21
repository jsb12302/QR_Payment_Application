package com.example.apitest.menu;

import com.example.apitest.message.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
public class MenuController {

    @Autowired
    MenuService menuService;

    @PostMapping("/addMenuRequest")
    @ResponseBody
    public Status regist_menu(@RequestBody MenuDTO menuDTO){
        menuService.addmenu(menuDTO);
        Status status=new Status();
        status.setStatus("ok");
        return status;
    }

    @PostMapping("/getMenuInfo")
    @ResponseBody
    public List<Menu> returnMenu(@RequestParam String loginId){
        return menuService.findMenu(loginId);
    }

//    @PostMapping("/getMenuImage")
//    @ResponseBody
//    public List<Menu> returnMenuImage(@RequestParam String loginId){
//        return menuService.findMenu(loginId);
//    }


    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map fileUpload(HttpServletRequest req, HttpServletResponse rep, @RequestParam Map<String, String> paramMap) {

        String menuName=paramMap.get("menuName");
        String menuPrice=paramMap.get("menuPrice");
        String menuDesc=paramMap.get("menuDesc");
        String loginId=paramMap.get("loginId");

        System.out.println("메뉴 이름 : " + menuName);

        String path = "C:\\menu\\"+loginId;
        String fileName = "";
        System.out.println("path : " + path);

        Map returnObject = new HashMap();
        try {
            MultipartHttpServletRequest mhsr = (MultipartHttpServletRequest) req;
            Iterator iter = mhsr.getFileNames();
            MultipartFile mfile = null;
            String fieldName = "";
            List resultList = new ArrayList();

            File dir = new File(path);
            if (!dir.isDirectory()) {
                dir.mkdirs();
            }

            while (iter.hasNext()) {
                fieldName = (String) iter.next();
                mfile = mhsr.getFile(fieldName);
                fileName = new String(mfile.getOriginalFilename().getBytes("8859_1"), "UTF-8");

                if ("".equals(fileName)) {
                    continue;
                }

                System.out.println("fileName : " + fileName);

                File serverFile = new File(path + File.separator + fileName);
                mfile.transferTo(serverFile);

                Map file = new HashMap();
                file.put("fileName", fileName);
                file.put("sfile", serverFile);
                resultList.add(file);
            }

            returnObject.put("files", resultList);
            returnObject.put("params", mhsr.getParameterMap());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuService.registMenu(menuName,menuPrice,menuDesc,loginId,path+"\\"+fileName);
        return null;
    }

}
