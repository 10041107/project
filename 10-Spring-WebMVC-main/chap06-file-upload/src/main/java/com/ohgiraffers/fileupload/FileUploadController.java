package com.ohgiraffers.fileupload;

import jakarta.annotation.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class FileUploadController {

    @Resource
    private ResourceLoader resourceLoader;


    @RequestMapping(value = {"/","/main"})
    public String index(){
        return "main";
    }
    //main.html에서 FileUploadController로 넘기고 result.html로 넘김

    @PostMapping("single-file")
    public String singleFileUpload(@RequestParam MultipartFile singleFile, String singleFileDescription, Model model) throws IOException {
        System.out.println("single file : " + singleFile);
        System.out.println("원본 파일 이름 : " + singleFile.getOriginalFilename()); //중요
        System.out.println("input name : " + singleFile.getName());
//        System.out.println("원본 파일 객체 : " + singleFile.getBytes()); // 중요
        System.out.println("원본 파일 사이즈 : " + singleFile.getSize()); // 중요
        System.out.println("singleFileDescription : " + singleFileDescription);
        // 파일 객체는 컴퓨터가 인식하는 실제 값

        //파일을 저장할 경로 설정
//        String root = "c:/upload-files";
//        String filePath = root+"/single";
        String filePath = resourceLoader.getResource("classpath:static/img/").getFile().getAbsolutePath();

        File dir = new File(filePath);

        if(!dir.exists()){ //경로가 없으면 만들겠다
            dir.mkdirs();
        }

        String originFileName = singleFile.getOriginalFilename();
        //오리진파일이름을 가져옴
        String ext = originFileName.substring(originFileName.lastIndexOf("."));
        //오리진파일 마지막인덱스에 .을 찍음 파일이름은 중복이되면 덮어 씌움
        String savedName = UUID.randomUUID().toString().replace("-","")+ext;
        //UUID는 랜덤으로 보내서 파일이름을 중복으로 만들지 않음 랜덤이름에 -가 들어갈수 있는데 그걸 빈칸으로 바꿔준다

        try{
            System.out.println("filePath =======================" + filePath);
            singleFile.transferTo(new File(filePath + "/" + savedName));
            model.addAttribute("message","파일 업로드 성공");
            model.addAttribute("img","static/img/"+savedName);
        }catch (IOException e){
            e.printStackTrace();
            model.addAttribute("message","파일 업로드 실패");
        }

        return "result";
    }

    @PostMapping("multi-file")
    public String multiFileUpload(@RequestParam List<MultipartFile> multipartFiles,
                                  String multiFileDescription, Model model){
        System.out.println("multiFiles : " + multipartFiles);
        System.out.println("multiFilesDescrtion : " + multiFileDescription);

        String root = "c:/upload-files";
        String filePath = root+"/multi";

        File dir = new File(filePath);
        if(!dir.exists()){
            dir.mkdirs(); // 폴더를 다수 만들 경우
//            dir.mkdir(); // 상위 폴더가 존재하는 경우
        }

        List<FileDTO> files = new ArrayList<>();

        try{
            for(MultipartFile file: multipartFiles){
                String originFileName = file.getOriginalFilename();
                String ext = originFileName.substring(originFileName.lastIndexOf("."));
                String savedName = UUID.randomUUID().toString().replace("-","")+ext;
                files.add(new FileDTO(originFileName,savedName,filePath,multiFileDescription));
                file.transferTo(new File(filePath,"/"+savedName));
            }
            model.addAttribute("message","다중 파일 업로드 성공");
        }catch (IOException e){
            e.printStackTrace();

            for (FileDTO file : files){
                new File(filePath+"/"+file.getSavedName()).delete();
            }
            model.addAttribute("message","다중 파일 업로드 실패");
        }
        return "result";
    }
}
