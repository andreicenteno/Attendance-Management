package com.am.service;
import java.util.List;
import com.am.model.SundayService;

public interface SundayServiceService {
	public void insert(SundayService entity);
	public List<SundayService> listSundayService();
	public SundayService getSundayService(Long id);
	public void delete(SundayService entity);
	public void update(SundayService entity);
}