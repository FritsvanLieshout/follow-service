package com.kwetter.frits.followservice.logic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kwetter.frits.followservice.configuration.KafkaProperties;
import com.kwetter.frits.followservice.entity.Follow;
import com.kwetter.frits.followservice.entity.FollowViewModel;
import com.kwetter.frits.followservice.logic.dto.FollowTimelineDTO;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class TimelineLogicImpl {

    private final Logger log = LoggerFactory.getLogger(TimelineLogicImpl.class);

    private final KafkaProperties kafkaProperties;

    private final static Logger logger = LoggerFactory.getLogger(TimelineLogicImpl.class);
    private KafkaProducer<String, String> producer;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public TimelineLogicImpl(KafkaProperties kafkaProperties) {
        this.kafkaProperties = kafkaProperties;
    }

    @PostConstruct
    public void initialize(){
        log.info("Kafka producer initializing...");
        this.producer = new KafkaProducer<>(kafkaProperties.getProducerProps());
        Runtime.getRuntime().addShutdownHook(new Thread(this::shutdown));
        log.info("Kafka producer initialized");
    }

    public void timeLineFollow(Follow follow) throws Exception {
        try {
            FollowTimelineDTO followTimelineDTO = new FollowTimelineDTO(follow.getUsername(), follow.getFollowingUsername());
            String message = objectMapper.writeValueAsString(followTimelineDTO);
            ProducerRecord<String, String> record = new ProducerRecord<>("user-follow", message);
            producer.send(record);
        } catch (JsonProcessingException e) {
            logger.error("Could not send tweet", e);
            throw new Exception(e);
        }
    }

    public void timeLineUnfollow(Follow follow) throws Exception {
        try {
            FollowTimelineDTO followTimelineDTO = new FollowTimelineDTO(follow.getUsername(), follow.getFollowingUsername());
            String message = objectMapper.writeValueAsString(followTimelineDTO);
            ProducerRecord<String, String> record = new ProducerRecord<>("user-unfollow", message);
            producer.send(record);
        } catch (JsonProcessingException e) {
            logger.error("Could not send tweet", e);
            throw new Exception(e);
        }
    }

    @PreDestroy
    public void shutdown() {
        log.info("Shutdown Kafka producer");
        producer.close();
    }
}
