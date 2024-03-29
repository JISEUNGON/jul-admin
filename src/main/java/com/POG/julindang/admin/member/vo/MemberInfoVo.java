package com.POG.julindang.admin.member.vo;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberInfoVo {
    private Long memberId;
    private String nickname;
    private String role;
    private String email;
}
