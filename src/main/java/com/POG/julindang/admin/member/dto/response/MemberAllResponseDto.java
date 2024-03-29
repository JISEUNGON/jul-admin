package com.POG.julindang.admin.member.dto.response;

import com.POG.julindang.admin.member.vo.MemberInfoVo;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberAllResponseDto {
    private List<MemberInfoVo> memberInfoVoList;
    private Long totalPageNumber;
}
