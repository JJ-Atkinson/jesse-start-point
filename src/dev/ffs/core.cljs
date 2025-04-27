(ns dev.ffs.core
  (:require
   [reagent.dom :as rdom]
   [re-frame.core :as rf]
   [dev.ffs.data :as events]
   [dev.ffs.views :as views]))



(defn dev-setup
  []
  (when goog.DEBUG
    (println "dev mode")))

(defn ^:dev/after-load mount-root
  []
  (rf/clear-subscription-cache!)
  (let [root-el (.getElementById js/document "app")]
    (rdom/unmount-component-at-node root-el)
    (rdom/render [views/hello-world] root-el)))

(defn init
  []
  (rf/dispatch-sync [::events/initialize-db])
  (dev-setup)
  (mount-root))