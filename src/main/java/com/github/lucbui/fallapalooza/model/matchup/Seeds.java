package com.github.lucbui.fallapalooza.model.matchup;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Iterator;
import java.util.List;

@Data
public class Seeds implements Iterable<Seeds.Seed> {
    @Size(min = 16, max = 16)
    @NonNull
    @ApiModelProperty(required = true)
    private List<Seed> seeds;

    @Override
    public Iterator<Seed> iterator() {
        return seeds.iterator();
    }

    @Data
    @AllArgsConstructor
    public static class Seed {
        @NotNull
        @ApiModelProperty("${api.matchup.teamOneSeed}")
        private Integer teamOne;

        @NotNull
        @ApiModelProperty("${api.matchup.teamTwoSeed}")
        private Integer teamTwo;
    }
}
