package kr.co.farmstory.service.market;

import java.util.List;

import kr.co.farmstory.dao.market.MarketDAO;
import kr.co.farmstory.dto.cart.CartDTO;
import kr.co.farmstory.dto.page.PageGroupDTO;
import kr.co.farmstory.dto.product.ProductDTO;
import kr.co.farmstory.dto.user.UserDTO;

public enum MarketService {

	INSTANCE;
	
	private MarketDAO dao = MarketDAO.getInstance();

	
	public int getCurrentPage(String page) {
		int currentPage = 1;
		if(page != null) {
			currentPage = Integer.parseInt(page);	
		}
		return currentPage;
	}
	
	public int getLastPageNum(int total) {
		int lastPageNum = (int) Math.ceil(total / 5.0);
		return lastPageNum;	
	}
	
	public int getStart(int currentPage) {
		return (currentPage - 1) * 5;
	}
	
	public int getCurrentStartNum(int total, int currentPage) {
		int start = (currentPage - 1) * 5;
		return total - start;
	}
	
	public PageGroupDTO getCurrentPageGroup(int currentPage, int lastPageNum) {
		int currentPageGroup = (int) Math.ceil(currentPage / 5.0);
		int pageGroupStart = (currentPageGroup - 1) * 5 + 1;
		int pageGroupEnd = currentPageGroup * 5;
		
		if(pageGroupEnd > lastPageNum) {
			pageGroupEnd = lastPageNum;			
		}
		return new PageGroupDTO(pageGroupStart, pageGroupEnd);	
	}
	
	public int selectCount() {
		return dao.selectCount();
	}
	
	public List<ProductDTO> selectAll(int start) {
		return dao.selectAll(start);
	}
	
	public int selectCountCate(String cate) {
	    return dao.selectCountCate(cate);
	}

	public List<ProductDTO> selectAllCate(String cate, int start) {
	    return dao.selectAllCate(cate, start);
	}
	
	public ProductDTO selectProduct(String prodNo) {
	    return dao.selectProduct(prodNo);
	}
	
	public int insertCart(CartDTO dto) {
		return dao.insertCart(dto);
	}
	
	public List<CartDTO> selectCartList(String userid) {
	    return dao.selectCartList(userid);
	}

    public int checkSoldOut(String userid, int prodNo) {
        return dao.checkSoldOut(userid, prodNo);
    }

    public int checkStockLack(String userid, int prodNo, int count) {
        return dao.checkStockLack(userid, prodNo, count);
    }

    public int updateCartCount(int count, String userid, int prodNo) {
        return dao.updateCartCount(count, userid, prodNo);
    }
    
    public int updateCartCount2(int count, String userid, int prodNo) {
        return dao.updateCartCount2(count, userid, prodNo);
    }

    public int checkDuplicateCart(String userid, int prodNo) {
        return dao.checkDuplicateCart(userid, prodNo);
    }
    
    public void deleteCart(int cartNo) {
    	dao.deleteCart(cartNo);
    }
    
    public List<CartDTO> selectCartListByCartNo(String[] cartNoArr) {
    	return dao.selectCartListByCartNo(cartNoArr);
    }
    
    public UserDTO selectOrderUser(String userid) {
        return dao.selectOrderUser(userid);
    }
    
    public List<kr.co.farmstory.dto.cart.CartDTO> selectProductBuyNowOrder(String countStr, String prodNoStr) {
        java.util.ArrayList<kr.co.farmstory.dto.cart.CartDTO> orderList = new java.util.ArrayList<>();
        
        try {
            int count = (countStr != null) ? Integer.parseInt(countStr) : 1;
            int prodNo = (prodNoStr != null) ? Integer.parseInt(prodNoStr) : 0;
            
            if (prodNo > 0) {
                CartDTO dto = dao.selectProductBuyNowOrder(count, prodNo);
                if (dto != null) {
                    orderList.add(dto);
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return orderList;
    }
    
}