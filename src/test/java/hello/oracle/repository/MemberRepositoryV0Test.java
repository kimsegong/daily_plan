package hello.oracle.repository;

import hello.oracle.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repository = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        Member member = new Member(5, "곽준옥", 01034);
        repository.save(member);

        Member findMember = repository.findById(member.getUserId());
        log.info("findMember={}", findMember);
        Assertions.assertThat(findMember).isEqualTo(member);
    }
}