(ns kwnew.json
  (:import [java.io StringWriter]
           [com.fasterxml.jackson.core JsonFactory JsonFactory$Feature JsonGenerator]))

(set! *warn-on-reflection* true)
(set! *unchecked-math* true)

(defonce jf (JsonFactory.))

(defn rand-map [n]
  (zipmap (repeatedly n #(str (rand)))
          (repeatedly n #(rand-int 100))))

(defn create-json [n]
  (let [writer (StringWriter.)
        jgen (.createGenerator ^JsonFactory jf writer)]
    (.writeStartObject jgen)
    (doseq [[^String k v] (rand-map n)]
      (.writeNumberField jgen k (long v)))
    (.writeEndObject jgen)
    (.close jgen)
    (.toString writer)))

(defn parse [^String json-string]
  (.configure ^JsonFactory jf JsonFactory$Feature/CANONICALIZE_FIELD_NAMES false)
  (let [jp (.createParser ^JsonFactory jf json-string)]
    (.nextToken jp) ;; expect START_OBJECT
    (loop [data (transient {})]
      (let [t (.nextToken jp)]
        (if (.isStructEnd t)
          (persistent! data)
          (recur (assoc! data
                         (keyword (.getText jp))
                         (.nextLongValue jp 0))))))))

(defn bench [n]
  (let [json (create-json 1000)]
    (dotimes [_ n]
      (time
       (dotimes [_ 100]
         (parse json))))))

(comment
  (bench 50))
