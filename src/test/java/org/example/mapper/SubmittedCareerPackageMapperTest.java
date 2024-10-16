package org.example.mapper;

import org.example.classes.SubmittedCareerPackage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
public class SubmittedCareerPackageMapperTest {

    @InjectMocks
    private SubmittedCareerPackageMapper submittedCareerPackageMapper = Mappers.getMapper(SubmittedCareerPackageMapper.class);

    @Test
    public void testSubmittedCareerPackageToSubmittedCareerPackageDTO() {
        SubmittedCareerPackage submittedCareerPackage = new SubmittedCareerPackage();
        submittedCareerPackage.setId(1);

        SubmittedCareerPackageDTO submittedCareerPackageDTO = submittedCareerPackageMapper.submittedCareerPackageToSubmittedCareerPackageDTO(submittedCareerPackage);
        assertEquals(submittedCareerPackage.getId(), submittedCareerPackageDTO.getId());
    }

    @Test
    public void testSubmittedCareerPackageDTOToSubmittedCareerPackage() {
        SubmittedCareerPackageDTO submittedCareerPackageDTO = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO.setId(1);


        SubmittedCareerPackage submittedCareerPackage = submittedCareerPackageMapper.submittedCareerPackageDTOToSubmittedCareerPackage(submittedCareerPackageDTO);
        assertEquals(submittedCareerPackageDTO.getId(), submittedCareerPackage.getId());
    }

    @Test
    public void testSubmittedCareerPackageListToSubmittedCareerPackageDTOList() {
        SubmittedCareerPackage submittedCareerPackage1 = new SubmittedCareerPackage();
        submittedCareerPackage1.setId(1);

        SubmittedCareerPackage submittedCareerPackage2 = new SubmittedCareerPackage();
        submittedCareerPackage2.setId(2);

        List<SubmittedCareerPackage> submittedCareerPackages = Arrays.asList(submittedCareerPackage1, submittedCareerPackage2);
        List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS = submittedCareerPackageMapper.submittedCareerPackageListToSubmittedCareerPackageDTOList(submittedCareerPackages);

        assertEquals(submittedCareerPackages.size(), submittedCareerPackageDTOS.size());
    }

    @Test
    public void testSubmittedCareerPackageDTOListToSubmittedCareerPackageList() {
        SubmittedCareerPackageDTO submittedCareerPackageDTO1 = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO1.setId(1);

        SubmittedCareerPackageDTO submittedCareerPackageDTO2 = new SubmittedCareerPackageDTO();
        submittedCareerPackageDTO2.setId(2);

        List<SubmittedCareerPackageDTO> submittedCareerPackageDTOS = Arrays.asList(submittedCareerPackageDTO1, submittedCareerPackageDTO2);
        List<SubmittedCareerPackage> submittedCareerPackages = submittedCareerPackageMapper.submittedCareerPackageDTOListToSubmittedCareerPackageList(submittedCareerPackageDTOS);

        assertEquals(submittedCareerPackageDTOS.size(), submittedCareerPackages.size());
    }
}
