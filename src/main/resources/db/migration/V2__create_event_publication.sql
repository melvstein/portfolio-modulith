CREATE TABLE public.event_publication (
  id UUID NOT NULL,
  completion_date TIMESTAMP(6) WITH TIME ZONE,
  event_type VARCHAR(255) NOT NULL,
  listener_id VARCHAR(255) NOT NULL,
  publication_date TIMESTAMP(6) WITH TIME ZONE NOT NULL,
  serialized_event VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE INDEX idx_event_publication_completion_date
    ON public.event_publication (completion_date);