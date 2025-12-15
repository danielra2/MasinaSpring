package mycode.masabiliardspring.view;

import mycode.masabiliardspring.dtos.MasinaDto;
import mycode.masabiliardspring.exceptions.MasinaAlreadyExistsException;
import mycode.masabiliardspring.service.MasinaCommandService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class View {
    private MasinaQuerryService masinaQuerryService;
    private MasinaCommandService masinaCommandService;
    private Scanner scanner;


    public View(MasinaCommandService masinaCommandService, MasinaQuerryService masinaQuerryService){
        this.masinaQuerryService =masinaQuerryService;
        this.masinaCommandService =masinaCommandService;
        this.scanner=new Scanner(System.in);
    }

    public void menu(){
        System.out.println("1->Afiseaza toate mesele");
        System.out.println("2->Sterge masini audi");
        System.out.println("3->Adauga masina");
    }

    public void play(){
        boolean merge=true;
        while (merge){
            this.menu();
            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    masinaQuerryService.();
                    break;
                case 2:
                    viewDeleteCar();
                case 3:
                    viewAddCar();


            }
        }
    }
    public void viewDeleteCar(){
        String marca="audi";
        masinaCommandService.deleteAllByName(marca);
    }

    public void viewAddCar(){
        MasinaDto masinaDto=  new MasinaDto("test",12,"test");

        try{
            masinaCommandService.createMasina(masinaDto);
        } catch (MasinaAlreadyExistsException e) {
            e.printStackTrace();
        }



    }
}
