(ns clojure-noob.core
  (:gen-class))


; Find the largest contiguous array
; http://whostolebenfrog.github.io/interviews/2014/06/27/contiguous.html

(def nums [-4 3 0 -1 -6 4 5 -2 3 -1 2 -5 4])


(defn find-largest-contiguous [nums]

  (loop [nums nums
         previous-stats {:max 0
                         :count 0
                         :start-index 0
                         :best-max 0
                         :best-count 0
                         :best-start-index 0}]

    (let [current-num (first nums)
          rest-nums (rest nums)]

      (println current-num previous-stats)

      (if (nil? current-num)

        previous-stats

        (do

          (def current-max (+ (:max previous-stats) current-num))
          (def current-count (inc (:count previous-stats)))

          (println "current-count " current-count)
          (println "current index " (+ (:start-index previous-stats) current-count -1))

          (if (<= current-max 0)

            (recur rest-nums {:max 0
                              :count 0
                              :start-index (+ (:start-index previous-stats) current-count)
                              :best-max (:best-max previous-stats)
                              :best-count (:best-count previous-stats)
                              :best-start-index (:best-start-index previous-stats)})

            (if (> current-max (:best-max previous-stats))

              (recur rest-nums {:max current-max
                                :count current-count
                                :start-index (:start-index previous-stats)
                                :best-max current-max
                                :best-count current-count
                                :best-start-index (:start-index previous-stats)})

              (recur rest-nums {:max current-max
                                :count current-count
                                :start-index (:start-index previous-stats)
                                :best-max (:best-max previous-stats)
                                :best-count (:best-count previous-stats)
                                :best-start-index (:best-start-index previous-stats)}))))))))





 (find-largest-contiguous nums)


