package com.xxl.job.admin.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;
import com.xxl.job.admin.core.model.XxlJobLog;

/**
 * job log
 * @author xuxueli 2016-1-12 18:03:06
 */
@Repository
public class XxlJobLogDao {
	
	@Resource
	public SqlSessionTemplate sqlSessionTemplate;

	
	public List<XxlJobLog> pageList(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("jobGroup", jobGroup);
		params.put("jobId", jobId);
		params.put("triggerTimeStart", triggerTimeStart);
		params.put("triggerTimeEnd", triggerTimeEnd);
		
		return sqlSessionTemplate.selectList("XxlJobLogMapper.pageList", params);
	}

	
	public int pageListCount(int offset, int pagesize, int jobGroup, int jobId, Date triggerTimeStart, Date triggerTimeEnd) {
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("offset", offset);
		params.put("pagesize", pagesize);
		params.put("jobGroup", jobGroup);
		params.put("jobId", jobId);
		params.put("triggerTimeStart", triggerTimeStart);
		params.put("triggerTimeEnd", triggerTimeEnd);
		
		return sqlSessionTemplate.selectOne("XxlJobLogMapper.pageListCount", params);
	}

	
	public XxlJobLog load(int id) {
		return sqlSessionTemplate.selectOne("XxlJobLogMapper.load", id);
	}

	
	public int save(XxlJobLog xxlJobLog) {
		return sqlSessionTemplate.insert("XxlJobLogMapper.save", xxlJobLog);
	}

	
	public int updateTriggerInfo(XxlJobLog xxlJobLog) {
		if (xxlJobLog.getTriggerMsg()!=null && xxlJobLog.getTriggerMsg().length()>2000) {
			xxlJobLog.setTriggerMsg(xxlJobLog.getTriggerMsg().substring(0, 2000));
		}
		return sqlSessionTemplate.update("XxlJobLogMapper.updateTriggerInfo", xxlJobLog);
	}

	
	public int updateHandleInfo(XxlJobLog xxlJobLog) {
		if (xxlJobLog.getHandleMsg()!=null && xxlJobLog.getHandleMsg().length()>2000) {
			xxlJobLog.setHandleMsg(xxlJobLog.getHandleMsg().substring(0, 2000));
		}
		return sqlSessionTemplate.update("XxlJobLogMapper.updateHandleInfo", xxlJobLog);
	}

	
	public int delete(int jobId) {
		return sqlSessionTemplate.delete("XxlJobLogMapper.delete", jobId);
	}

	
	public int triggerCountByHandleCode(int handleCode) {
		return sqlSessionTemplate.selectOne("XxlJobLogMapper.triggerCountByHandleCode", handleCode);
	}

	
	public List<Map<String, Object>> triggerCountByDay(Date from, Date to, int handleCode) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("from", from);
		params.put("to", to);
		params.put("handleCode", handleCode);
		return sqlSessionTemplate.selectList("XxlJobLogMapper.triggerCountByDay", params);
	}

	
	public int clearLog(int jobGroup, int jobId, Date clearBeforeTime, int clearBeforeNum) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("jobGroup", jobGroup);
		params.put("jobId", jobId);
		params.put("clearBeforeTime", clearBeforeTime);
		params.put("clearBeforeNum", clearBeforeNum);
		return sqlSessionTemplate.delete("XxlJobLogMapper.clearLog", params);
	}

}
