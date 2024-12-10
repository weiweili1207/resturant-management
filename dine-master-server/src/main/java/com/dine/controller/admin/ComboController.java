package com.dine.controller.admin;

import com.dine.dto.ComboDTO;
import com.dine.dto.ComboPageQueryDTO;
import com.dine.result.PageResult;
import com.dine.result.Result;
import com.dine.service.ComboService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.util.PackageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.*;

@RestController
@Configuration
@RequestMapping("/admin/setmeal")
@Slf4j
@Api("Combo-related API")
public class ComboController {
    @Autowired
    private ComboService comboService;

    @PostMapping
    @ApiOperation("add new combo")
    public Result add(@RequestBody ComboDTO comboDTO) {
        log.info("Starting to add new combo: {}", comboDTO);
        comboService.addCombo(comboDTO);
        return Result.success();
    }

    @GetMapping("page")
    @ApiOperation("combo pagination")
    public Result<PageResult> page(ComboPageQueryDTO comboPageQueryDTO) {
        log.info("Starting to pagination for combo: {}", comboPageQueryDTO);
        PageResult page = comboService.pageQuery(comboPageQueryDTO);
        return Result.success(page);
    }
}
