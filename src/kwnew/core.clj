(ns kwnew.core)


(set! *warn-on-reflection* true)

(set! *unchecked-math* true)

(defn kw-new [n unique?]
  (let [base (if unique? (str (rand)) "abcdef")]
    (loop [i 0
           kws (transient [])]
      (if (< i n)
        (recur (inc i) (conj! kws (keyword (str base i))))
        (persistent! kws)))))

(defn bench-kw [reps n unique?]
  (dotimes [_ reps]
    (let [begin (System/nanoTime)]
        (kw-new n unique?)
        (let [end (System/nanoTime)
              elapsed (/ (- end begin) 1000000.0)]
          (println elapsed "ms")))))

(comment
  (bench-kw 50 10000 false)
  (bench-kw 50 10000 true))

(def ks
  ["web-app" "web-app" "servlet" "servlet-mapping" "taglib" "servlet-name" "servlet-class" "init-param" "dataStoreUrl" "redirectionClass" "templateOverridePath" "dataStoreLogLevel" "searchEngineFileTemplate" "useJSP" "cachePackageTagsTrack" "dataStoreUser"
   "cachePagesDirtyRead" "useDataStore" "configGlossary:installationAt" "configGlossary:poweredByIcon" "configGlossary:adminEmail" "cachePackageTagsStore" "dataStoreDriver" "dataStorePassword" "dataStoreClass" "configGlossary:poweredBy" "dataStoreLogFile"
   "cacheTemplatesTrack" "cachePagesTrack" "searchEngineListTemplate" "maxUrlLength" "templatePath" "dataStoreTestQuery" "configGlossary:staticPath" "dataStoreInitConns" "dataStoreName" "searchEngineRobotsDb" "dataStoreMaxConns" "dataStoreConnUsageLimit"
   "cacheTemplatesRefresh" "jspListTemplate" "cachePagesStore" "templateLoaderClass" "defaultFileTemplate" "templateProcessorClass" "cacheTemplatesStore" "jspFileTemplate" "cachePagesRefresh" "defaultListTemplate" "cachePackageTagsRefresh" "servlet-name"
   "servlet-class" "init-param" "mailHost" "mailHostOverride" "servlet-name" "servlet-class" "servlet-name" "servlet-class" "servlet-name" "servlet-class" "init-param" "log" "adminGroupID" "dataLogLocation" "removePageCache" "templatePath" "lookInContext"
   "dataLogMaxSize" "fileTransferFolder" "dataLog" "betaServer" "removeTemplateCache" "logMaxSize" "logLocation" "cofaxCDS" "cofaxEmail" "cofaxAdmin" "fileServlet" "cofaxTools" "taglib-uri" "taglib-location"])

(defn bench-kyle [reps]
  (dotimes [_ reps]
    (time
     (dotimes [_ 1000]
       (doall (map keyword ks))))))
