package mycode.masabiliardspring.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MasinaListResponsePageable {
    private List<MasinaResponse> masini;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private int pageSize;
}