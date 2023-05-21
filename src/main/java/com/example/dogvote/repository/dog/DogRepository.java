package com.example.dogvote.repository.dog;

import com.example.dogvote.dto.dog.request.DogCreateRequest;
import com.example.dogvote.dto.dog.response.DogDetailResponse;
import com.example.dogvote.dto.dog.response.DogResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DogRepository {

    private final JdbcTemplate jdbcTemplate;

    public DogRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addDog(DogCreateRequest request) {
        String sql = "INSERT INTO dog (name, description, photo_url, vote_count) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, request.getName(), request.getDescription(), request.getPhotoUrl(), 0);
    }

    public List<DogResponse> getDogs() {
        //TODO: redis에 캐시된 정보가 있을 경우 -> 레디스에서 정보 가져오기

        //없다면 DB 에서 가져오기
        //TODO: DB에서 가져온 뒤 redis에 갱신해준 뒤 응답.
        String sql = "SELECT id, name, photo_url, vote_count FROM dog";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            String name = rs.getString("name");
            String photoUrl = rs.getString("photo_url");
            long voteCount = rs.getLong("vote_count");
            return new DogResponse(id, name, photoUrl, voteCount);
        });
    }

    public DogDetailResponse getDetailDog(long id) {
        //TODO: redis에 캐시된 정보가 있을 경우 -> 레디스에서 정보 가져오기

        //없다면 DB 에서 가져오기
        //TODO: DB에서 가져온 뒤 redis에 갱신해준 뒤 응답.
        String sql = "SELECT * FROM dog WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long getId = rs.getLong("id");
            String name = rs.getString("name");
            String description = rs.getString("description");
            String photoUrl = rs.getString("photo_url");
            long voteCount = rs.getLong("vote_count");
            return new DogDetailResponse(getId, name, description, photoUrl, voteCount);
        }, id);
    }

    public boolean isDogNotExist(long id) {
        String readSql = "SELECT * FROM dog WHERE id = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();
    }

    public void updateDogVoteCount(long id, boolean isDogNotVoted) {
        //TODO: Kafka를 통해 투표인지 투표 취소인지 확인하여 투표라면 ++, 아니라면 --;
        String sql;
        if (isDogNotVoted){
            sql = "UPDATE dog SET vote_count = vote_count + 1 WHERE id = ?";
        }
        else {
            sql = "UPDATE dog SET vote_count = vote_count - 1 WHERE id = ?";
        }
        jdbcTemplate.update(sql, id);
    }

}
