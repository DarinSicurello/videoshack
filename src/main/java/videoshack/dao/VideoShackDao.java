package videoshack.dao;

//FINAL Created Video Dao interface

import org.springframework.data.jpa.repository.JpaRepository;

import videoshack.entity.VideoShack;


public interface VideoShackDao extends JpaRepository<VideoShack, Long> {

}
