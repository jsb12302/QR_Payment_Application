package com.example.apitest.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/fileDownload/{storeName}", method = RequestMethod.GET)
    public ResponseEntity<?> readOne(@PathVariable final String storeName) {
        String path = menuService.getStoreByStoreName(storeName);
        return ResponseEntity.ok(path);
    }

    @RequestMapping(value = "/fileUpload", method = RequestMethod.POST)
    public Map fileUpload(HttpServletRequest req, HttpServletResponse rep, @RequestParam Map<String, String> paramMap) {
        String storeName = paramMap.get("storeName");
        String menuName = paramMap.get("menuName");
        System.out.println("storeName : " + storeName);

        String path = "C:\\menu\\";
        String fileName = "";
        System.out.println("menuName : " + menuName);
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
        menuService.createMenu(storeName, menuName, path, fileName);
        return null;
    }
}
