package hello.advanced.trace.hellotrace;

import hello.advanced.trace.TraceId;
import hello.advanced.trace.TraceStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloTraceV1 {

	private static final String START_PREFIX = "-->";
	private static final String COMPLETE_PREFIX = "<--";
	private static final String EX_PREFIX = "<X-";


	private TraceStatus begin(String message) {
		TraceId traceId = new TraceId();
		Long startTimeMs = System.currentTimeMillis();
		log.info("[{}] {}{}", traceId.getId(), addSpace(START_PREFIX, traceId.getLevel()), message);
		//로그 출력
		return new TraceStatus(traceId, startTimeMs, message);
	}

	public void end(TraceStatus status) {
		complete(status, null);
	};

	public void exception(TraceStatus status, Exception e) {
		complete(status, e);
	}
	private void complete(TraceStatus status, Exception e) {
		Long stopTimeMs = System.currentTimeMillis();
	}

	private Object addSpace(String startPrefix, int level) {
		return null;
	}
}
