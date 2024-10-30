package com.jcerdar.gestorfutbol.service;

import com.jcerdar.gestorfutbol.apirest.v1.model.Filtre;
import com.jcerdar.gestorfutbol.persistence.dao.CampanyaDao;
import com.jcerdar.gestorfutbol.persistence.dao.PatrocinadorDao;
import com.jcerdar.gestorfutbol.persistence.dao.SociDao;
import com.jcerdar.gestorfutbol.persistence.model.Campanya;
import com.jcerdar.gestorfutbol.service.model.CampanyaDTO;
import com.jcerdar.gestorfutbol.service.model.PaginaDTO;
import com.jcerdar.gestorfutbol.service.model.PatrocinadorDTO;
import com.jcerdar.gestorfutbol.service.model.SociDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class GestorFutbolServiceImpl implements GestorFutbolService{

    @Autowired
    private CampanyaDao campanyaDao;

    @Autowired
    private SociDao sociDao;

    @Autowired
    private PatrocinadorDao patrocinadorDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PaginaDTO<List<CampanyaDTO>> listCampanyas(Filtre filtre) {
        Page<Campanya> campanyas = campanyaDao.findAll(PageRequest.of(filtre.getPageNum(), filtre.getPageSize()));
        PaginaDTO<List<CampanyaDTO>> paginaDTO = new PaginaDTO<>();
        List<CampanyaDTO> campanyaDTOS = new ArrayList<>();
        if(campanyas.getTotalElements() > 0) {
            campanyaDTOS = campanyas.map(campanya -> modelMapper.map(campanya, CampanyaDTO.class)).getContent();
            paginaDTO.setTotal(campanyas.getTotalElements());
            paginaDTO.setResult(campanyaDTOS);
        }
        return paginaDTO;
    }

    @Override
    public Long getCampanyaActivaId() {
        Campanya campanya = campanyaDao.findCampanyaByActivaIsTrue();
        if(campanya != null) {
            return campanya.getId();
        }
        return null;
    }

    @Override
    public Long saveCampanya(CampanyaDTO campanyaDTO) {
        Campanya campanya = modelMapper.map(campanyaDTO, Campanya.class);
        campanya = campanyaDao.save(campanya);
        return campanya.getId();
    }

    @Override
    public void deleteCampanya(Long id) {
        campanyaDao.deleteById(id);
    }

    @Override
    public PaginaDTO<List<SociDTO>> listSocis(Filtre filtre) {
        return null;
    }

    @Override
    public Long saveSoci(SociDTO sociDTO) {
        return null;
    }

    @Override
    public void deleteSoci(Long id) {

    }

    @Override
    public PaginaDTO<List<PatrocinadorDTO>> listPatrocinador(Filtre filtre) {
        return null;
    }

    @Override
    public Long savePatrocinador(PatrocinadorDTO patrocinadorDTO) {
        return null;
    }

    @Override
    public void deletePatrocinador(Long id) {

    }
}
