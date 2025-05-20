package com.tfg.backend.services.operations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tfg.backend.api.request.StockRequest;
import com.tfg.backend.data.ErrorMessages.ErrorMessageRNF;
import com.tfg.backend.exceptions.exceptions.ResourceNotFoundException;
import com.tfg.backend.models.Boardgame;
import com.tfg.backend.models.Stock;
import com.tfg.backend.models.User;
import com.tfg.backend.repository.BoardgameRepository;
import com.tfg.backend.repository.StockRepository;
import com.tfg.backend.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BoardgameRepository boardgameRepository;


    public void addStock(StockRequest stockRequest) {
        Boardgame boardgame = boardgameRepository.findById(stockRequest.FkBoardgameRq())
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_RNF));
        User user = userRepository.findById(stockRequest.FkUserRq())
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));

        Optional<Stock> existingStock = stockRepository.findByFkUserAndFkBoardgame(user, boardgame);

        if (existingStock.isPresent()) {
            updateStock(existingStock.get().getStockId(), stockRequest);
        }else{
            Stock stock = new Stock();
            stock.setUnits(stockRequest.unitsRq());
            stock.setFkUser(user);
            stock.setFkBoardgame(boardgame);       
            stockRepository.save(stock);
        }

    }

    public void updateStock(Integer id, StockRequest stockRequest) {
        Stock stock = stockRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.STOCK_RNF));
        Boardgame boardgame = boardgameRepository.findById(stockRequest.FkBoardgameRq())
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.BOARDGAME_RNF));
        User user = userRepository.findById(stockRequest.FkUserRq())
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));

        stock.setUnits(stockRequest.unitsRq());
        stock.setFkUser(user);
        stock.setFkBoardgame(boardgame);
        stockRepository.save(stock);
    }

    public void deleteStock(Integer stockId) {
        if (stockRepository.existsById(stockId)) {
            stockRepository.deleteById(stockId);
        }   
    }

    public List<Stock> getAllStocksByUser(Integer userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException(ErrorMessageRNF.USER_RNF));
        return stockRepository.findByFkUser(user);
    }
}