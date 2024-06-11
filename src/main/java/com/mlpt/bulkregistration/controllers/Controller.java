package com.mlpt.bulkregistration.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mlpt.bulkregistration.entity.Approval;
import com.mlpt.bulkregistration.model.MasterUserResponse;
import com.mlpt.bulkregistration.model.RegisterCustomerResponse;
import com.mlpt.bulkregistration.services.CustomerService;

@RestController
@RequestMapping
public class Controller {

    @Value("${upload.directory}")
	private String dir;

    @Value("${done.directory}")
	private String doneFolder;

    @Value("${log.file.directory}")
	private String logFileFolder;

    @Autowired
	private CustomerService customerService;

    private static final Log log = LogFactory.getLog(CustomerService.class);

    @GetMapping("/api/bulk")
    public String BulkRegistration(@RequestParam String filename) throws FileNotFoundException, IOException{
        File file = new File(dir+filename);
        if (file.isFile()){
            FileWriter logFile = createLogFile(file);
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                br.readLine();
                String line;
                int i=1;
                while ((line = br.readLine()) != null) {
                    String writeLine = line;
                    log.info("line : "+line);
                    writeLine = writeLine +";"+ validateLine(line);
                    if (validateLine(line)){
                        String[] values = line.split(";");
                        long before = System.currentTimeMillis();
                        try{
                            MasterUserResponse masterUserResponse = customerService.getMasterUserResponse(values[0]);
                            writeLine = writeLine + ";" + masterUserResponse.getRc();
                            if (masterUserResponse.getRc().equals("100")){
                                MasterUserResponse  newMasterUserResponse = customerService.filterAccount(masterUserResponse);
                                try{
                                    RegisterCustomerResponse registerCustomerResponse = customerService.register(newMasterUserResponse, values[2], values[1], values[0]);
                                    writeLine = writeLine + ";" + registerCustomerResponse.getRc();
                                    if (registerCustomerResponse.getRc().equals("100")){
                                        if (registerCustomerResponse.getReffNum()!=null){
                                            String listAccountsNumber = "{";
                                            for (int j = 0; j<newMasterUserResponse.getAccounts().size(); j++){
                                                listAccountsNumber = listAccountsNumber + newMasterUserResponse.getAccounts().get(j).getAccountNo();
                                                if ((j+1)<newMasterUserResponse.getAccounts().size()) listAccountsNumber = listAccountsNumber + ",";
                                            }
                                            listAccountsNumber = listAccountsNumber + "}";
                                            writeLine = writeLine + ";" + registerCustomerResponse.getReffNum() +";"+ newMasterUserResponse.getCustomerName()+";"+newMasterUserResponse.getShortName()
                                                        +";"+newMasterUserResponse.getLastName()+";"+newMasterUserResponse.getBirthDate()+";"+newMasterUserResponse.getAddress()
                                                        +";"+newMasterUserResponse.getCif()+";"+newMasterUserResponse.getSex()+";"+listAccountsNumber;
                                            Approval approval = customerService.findApprovalByreffNumRegistrationandChannel(registerCustomerResponse.getReffNum(), values[2]);
                                            if (approval!=null){
                                                Long idApproval = approval.getId();
                                                writeLine = writeLine + ";" + idApproval;
                                                try{
                                                    String custApprovalResponse = customerService.approvalResponse(String.valueOf(idApproval));
                                                    writeLine = writeLine +";"+ custApprovalResponse;
                                                    if (custApprovalResponse.equals("100")){
                                                        long after = System.currentTimeMillis();
                                                        writeLine = writeLine + ";" + (after-before);
                                                        log.info("line "+i+" : "+line+" completed, execution time : "+(after-before)+" ms");
                                                    } else {
                                                        writeLine = writeLine + ";";
                                                        log.info("rc approval : "+custApprovalResponse+", rc approval not 100");
                                                    } 
                                                }catch (Exception e) {
                                                    writeLine = writeLine + ";approval not response;";
                                      //              logFile.write(line + ";" + validateLine(line)+ ";S RC("+masterUserResponse.getRc()+");S RC("+registerCustomerResponse.getRc()+");"+registerCustomerResponse.getReffNum()+";;;;;;;;;;;;;;;;approval not response;" + System.getProperty("line.separator"));
                                                    log.info("approval not response");
                                                }	
                                            } else{
                                                writeLine = writeLine + ";approval not found;;";
                                        //        logFile.write(line + ";" + validateLine(line)+ ";S RC("+masterUserResponse.getRc()+");S RC("+registerCustomerResponse.getRc()+");"+registerCustomerResponse.getReffNum()+";;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                                                log.info("approval : "+approval+"approval not found");
                                            }
                                        } else{
                                            writeLine = writeLine + ";reff num null;;;;;;;;;;;";
                                      //      logFile.write(line + ";" + validateLine(line)+ ";S RC("+masterUserResponse.getRc()+");S RC("+registerCustomerResponse.getRc()+");reff Number null;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                                            log.info("reff num null");
                                        }
                                    }else{
                                        writeLine = writeLine + ";;;;;;;;;;;;";
                                  //      logFile.write(line + ";" + validateLine(line)+ ";S RC("+masterUserResponse.getRc()+");F RC("+registerCustomerResponse.getRc()+");;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                                        log.info("rc register not 100");
                                    }
                                }catch (Exception e) {
                                    writeLine = writeLine + ";register not response;;;;;;;;;;;";
                            //        logFile.write(line + ";" + validateLine(line)+ ";S RC("+masterUserResponse.getRc()+");register not response;;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                                    log.info("register not response");
                                }
                            }
                            else{
                                writeLine = writeLine + ";;;;;;;;;;;;";
                                //add rc to log, next row
                               // logFile.write(line + ";" + validateLine(line)+ ";F RC("+masterUserResponse.getRc()+");;;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                                log.info("rc master user : "+masterUserResponse.getRc()+", rc master user tidak 100");
                            }
                        }catch (Exception e) {
                            writeLine = writeLine + ";get master data not response;;;;;;;;;;;;";
                          //  logFile.write(line + ";" + validateLine(line)+ ";get master data not response;;;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                            log.info("get master data not response");
                        }
                    }else{
                        writeLine = writeLine + ";;;;;;;;;;;;";
                    //    logFile.write(line + ";" + validateLine(line)+ ";;;;;;;;;;;;;;;;;;;;" + System.getProperty("line.separator"));
                        log.info("line not valid");
                    }
                    log.info("write line : "+writeLine);
                    logFile.write(writeLine + System.getProperty("line.separator"));
                    i++;
                }

            }
            logFile.close();    
            moveFile(file);
            return "done"; 
        }else {
            return "file not found";
        }
    }

    public boolean validateLine(String line){
        String[] values = line.split(";");
        if (values.length!=3){
            return false;
        }else if(values[0].isEmpty()){
            return false;
        }else if(!values[0].matches("^[0-9]*")){
            return false;
        }else if(values[0].length()!=16){
            return false;
        }else if(values[1].isEmpty()){
            return false;
        }else if(!values[1].matches("^0[1-9][0-9]*")){
            return false;
        }else if(values[1].length()<9 || values[1].length()>13){
            return false;
        }else if(!values[2].equals("RIB") && !values[2].equals("MB")){
            return false;
        }
        return true;
    }

    public void moveFile(File file){
        final File done = new File(doneFolder);
        if (!done.exists()) {
            done.mkdirs();
        }

        final File doneFile = new File(doneFolder + file.getName() + "." + new Date().getTime() + ".done");
        if (doneFile.exists()) {
            doneFile.delete();
        //    logger.info(doneFile.getAbsolutePath() + " exist, Deleting old file...");
        }
        if (file.renameTo(doneFile)) {
        //    logger.warn("Move Failed File to " + pl.getValue("app.folder.failed") + teksFile.getName());
        } 
        else {
            while (!file.renameTo(doneFile)) {
                if (!file.exists()) {
        //            logger.info("Move Success File to " + pl.getValue("app.folder.success") + teksFile.getName());
                    break;
                }
            }
        }
    }

    public FileWriter createLogFile(File file) throws IOException{
        final File lf = new File(logFileFolder);
        if (!lf.exists()) {
            lf.mkdirs();
        }
        FileWriter logFile = new FileWriter(logFileFolder + file.getName().replace(".csv", ".log"));
        logFile.write("atmCardNumber;requestPhoneNumber;channel;validateLine;getMasterDataStatus;registrationStatus;reffNumber;customerName;shortName;lastName;birthDate;address;cif;sex;accounts;idApproval;approvalStatus;executionTime(ms)" + System.getProperty("line.separator"));
        return logFile;
    }
    
}
