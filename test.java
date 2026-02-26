@Component
public class JobRunner implements ApplicationRunner {

    private final JobLauncher jobLauncher;
    private final Job dummyJob;

    public JobRunner(JobLauncher jobLauncher, Job dummyJob) {
        this.jobLauncher = jobLauncher;
        this.dummyJob = dummyJob;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        jobLauncher.run(dummyJob,
                new JobParametersBuilder()
                        .addLong("time", System.currentTimeMillis())
                        .toJobParameters());
    }
}
