@Configuration
public class DummyJobConfig {

    @Bean
    public Job dummyJob(JobRepository jobRepository,
                        PlatformTransactionManager transactionManager) {

        return new JobBuilder("dummyJob", jobRepository)
                .start(dummyStep(jobRepository, transactionManager))
                .build();
    }

    @Bean
    public Step dummyStep(JobRepository jobRepository,
                          PlatformTransactionManager transactionManager) {

        return new StepBuilder("dummyStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {

                    System.out.println("=== Loader started successfully ===");

                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }
}
