package ru.beleychev.pianotask.service;

import org.springframework.stereotype.Service;
import ru.beleychev.pianotask.domain.Item;

import java.util.List;

/**
 * @author beleychev
 */
@Service
public class StackExchangeApiRESTServiceImpl implements StackExchangeApiRESTService {
    @Override
    public List<Item> getItems(String title) {
        return null;
    }
}

