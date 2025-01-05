package hello.advanced.trace;

public class TraceStatus {
    private TraceId traceId;
    private Long startTime;

    private String message;

    public TraceStatus(TraceId traceId, Long startTime, String message){
        this.message =message;
        this.traceId = traceId;
        this.startTime = startTime;
    }


    public Long getStartTimeMs() {
        return startTime;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public String getMessage() {
        return message;
    }
}
