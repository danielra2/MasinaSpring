package mycode.masabiliardspring.dtos;

import java.util.Objects;

public record MasinaIdMarcaCuloareInfo(
        Long id,
        String marca,
        String culoare
) {

    @Override

    public boolean equals(Object obj){
        if(this==obj) {
            return true;
        }
        if(obj==null||getClass()!=obj.getClass()){
            return false;
        }

        MasinaIdMarcaCuloareInfo other=(MasinaIdMarcaCuloareInfo)obj;
        return Objects.equals(marca,other.marca())&&Objects.equals(culoare,other.culoare());



    }

    @Override
    public int hashCode(){
        return Objects.hash(marca,culoare);
    }

}