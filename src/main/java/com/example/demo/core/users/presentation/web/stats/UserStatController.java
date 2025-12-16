package com.example.demo.core.users.presentation.web.stats;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.demo.core.users.presentation.web.stats.UserStatsPath.BASE_STAT_PATH;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(path = BASE_STAT_PATH)
public class UserStatController {


}
