package com.projectteam.coop.web.session;

import com.projectteam.coop.domain.member.model.entity.Member;
import com.projectteam.coop.domain.member.enums.MemberType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSessionDto {
    private Long id;

    private String email;

    private String name;

    private MemberType type;

    private int point;

    public static MemberSessionDto createSession(Member member) {
        MemberSessionDto session = new MemberSessionDto();
        session.setId(member.getMemberNo());
        session.setEmail(member.getEmail());
        session.setName(member.getName());
        session.setType(member.getType());
        session.setPoint(member.getPoint());

        return session;
    }
}
